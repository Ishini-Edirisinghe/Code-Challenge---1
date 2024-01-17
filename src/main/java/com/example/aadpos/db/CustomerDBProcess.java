package com.example.aadpos.db;

import com.example.aadpos.dto.CustomerDTO;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBProcess {
    final static Logger logger = LoggerFactory.getLogger(CustomerDBProcess.class);
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customer(cus_ID,name,address,salary) VALUES (?,?,?,?)");
            ps.setString(1, customerDTO.getCus_id());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getAddress());
            ps.setDouble(4, customerDTO.getSalary());

            if (ps.executeUpdate() != 0) {
                logger.info("Data saved");
                return true;
            } else {
                logger.error("Failed to save");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CustomerDTO> getAllCustomer(Connection connection) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        try {
            ResultSet resultSet = connection.prepareStatement("select * from customer").executeQuery();
            while(resultSet.next()) {
                CustomerDTO customerDTO = new CustomerDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );
                customerDTOS.add(customerDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customerDTOS;
    }

    public boolean deleteCustomer(String cusId, Connection connection) {
        try {
            var ps = connection.prepareStatement("DELETE from customer where cus_id=?");
            ps.setString(1, cusId);

            if (ps.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement("UPDATE customer set name=?, address=? ,salary=? where cus_id=?");
            ps.setString(1, customerDTO.getName());
            ps.setString(2, customerDTO.getAddress());
            ps.setDouble(3, customerDTO.getSalary());
            ps.setString(4, customerDTO.getCus_id());

            if (ps.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
