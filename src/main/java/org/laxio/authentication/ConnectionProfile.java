package org.laxio.authentication;

import org.laxio.network.connection.Connection;

public interface ConnectionProfile extends Profile {

    Connection getConnection();

    boolean login();

}
