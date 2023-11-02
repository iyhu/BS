package Mapper;
import common.Friend;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendMapper implements RowMapper<Friend> {

    @Override
    public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
        Friend friend = new Friend();
        friend.setName(rs.getString("name"));
        friend.setPhoneNumber(rs.getString("phone_number"));
        friend.setAddress(rs.getString("address"));
        return friend;
    }
}
