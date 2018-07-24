package com.fongwell.satchi.crm.core.product.service.inventory;

import com.fongwell.satchi.crm.core.order.event.OrderCancelledEvent;
import com.fongwell.satchi.crm.core.order.event.OrderEventItem;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.Inventory;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.InventoryAudit;
import com.fongwell.satchi.crm.core.product.repository.InventoryAuditRepository;
import com.fongwell.satchi.crm.core.product.repository.InventoryRepository;
import com.fongwell.satchi.crm.core.product.service.inventory.exception.InsufficientInventoryException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 4/20/18.
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "inventoryRepository")
    private InventoryRepository inventoryRepository;

    @Resource(name = "inventoryAuditRepository")
    private InventoryAuditRepository inventoryAuditRepository;

    @Override
    public Map<Long, Integer> getInventory(final Collection<Long> skuIds) {

        return jdbcTemplate.execute(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con) throws SQLException {
                StringBuilder sql = new StringBuilder(32);
                sql.append("select sku_id, inventory from crm_sku_inventory where sku_id in (");
                sql.append(StringUtils.join(skuIds, ",")).append(")");
                return con.prepareStatement(sql.toString());
            }
        }, new PreparedStatementCallback<Map<Long, Integer>>() {
            @Override
            public Map<Long, Integer> doInPreparedStatement(final PreparedStatement ps) throws SQLException, DataAccessException {

                ResultSet rs = ps.executeQuery();

                Map<Long, Integer> result = new HashMap<>((int) (skuIds.size() / 0.75f) + 1);
                while (rs.next()) {
                    result.put(rs.getLong(1), rs.getInt(2));
                }
                return result;
            }
        });
    }

    @Override
    public void decrementInventoryForCheckout(long orderId, long customerId, final Map<Long, Integer> values) {


        Collection<Inventory> inventories = inventoryRepository.findAndLockBySkuIds(values.keySet());
        for (final Inventory inventory : inventories) {
            Integer value = values.get(inventory.getSku());
            if (value != null) {
                Integer currentInventory = inventory.getInventory();
                if (currentInventory != null && currentInventory >= value) {
                    inventory.decrease(value);
                    inventoryRepository.save(inventory);

                    InventoryAudit audit = new InventoryAudit(inventory.getSku(), value, currentInventory, inventory.getInventory(), customerId, orderId, "order");
                    inventoryAuditRepository.save(audit);
                } else {
                    throw new InsufficientInventoryException("Insufficient inventory for sku " + inventory.getSku(), Collections.singleton(inventory.getSku()));
                }
            }
        }
    }

    @Override
    public void onOrderCancelledEvent(final OrderCancelledEvent event) {

        Collection<OrderEventItem> items = event.getItems();

        if (items.isEmpty()) {
            return;
        }
        Map<Long, OrderEventItem> itemMap = new HashMap<>(items.size(), 2f);


        for (final OrderEventItem item : items) {
            if (item.getQuantity() != 0) {
                itemMap.put(item.getSkuId(), item);
            }

        }
        Collection<Inventory> inventories = inventoryRepository.findAndLockBySkuIds(itemMap.keySet());
        for (final Inventory inventory : inventories) {
            OrderEventItem item = itemMap.get(inventory.getSku());
            if (item != null) {
                inventory.increase(item.getQuantity());
            }
        }
        inventoryRepository.save(inventories);

    }


    @Override
    public Class<?> getTargetClass() {
        return InventoryServiceImpl.class;
    }
}
