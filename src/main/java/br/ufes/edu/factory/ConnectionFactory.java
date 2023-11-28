package br.ufes.edu.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final DataSource dataSource;

    public ConnectionFactory() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://trabalhofinalbd-db-1:3306/sistema?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        this.dataSource = comboPooledDataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
