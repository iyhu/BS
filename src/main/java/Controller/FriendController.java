package Controller;

import Mapper.FriendMapper;
import common.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ResponseBody
@RestController
@RequestMapping("/friends")
public class FriendController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping
    public List<Friend> getAllFriends() {
        String sql = "SELECT * FROM fr";
        List<Friend> friends = jdbcTemplate.query(sql, new FriendMapper());
        return friends;
    }
    // 添加联系人
    @PostMapping("/")
    public void addContact(@RequestBody Friend friend) {
        String sql = "INSERT INTO fr (name, phonenumber, address) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, friend.getName(), friend.getPhoneNumber(), friend.getAddress());
    }

    // 删除联系人
    @DeleteMapping("/{phoneNumber}")
    public void deleteContact(@PathVariable String phoneNumber) {
        String sql = "DELETE FROM fr WHERE phonenumber=?";
        jdbcTemplate.update(sql, phoneNumber);
    }

    // 根据名字查找联系人
    @GetMapping("/searchByPhone/{name}")
    public List<Friend> searchFriendsByName(@PathVariable String name) {
        String sql = "SELECT * FROM fr WHERE name=?";
        return jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) ->
                new Friend(rs.getString("name"), rs.getString("phone_number"), rs.getString("address")));
    }

    // 根据电话号码查找联系人
    @GetMapping("/searchByPhoneNumber/{phoneNumber}")
    public List<Friend> searchFriendsByPhoneNumber(@PathVariable String phoneNumber) {
        String sql = "SELECT * FROM fr WHERE phonenumber=?";
        return jdbcTemplate.query(sql, new Object[]{phoneNumber}, (rs, rowNum) ->
                new Friend(rs.getString("name"), rs.getString("phone_number"), rs.getString("address")));
    }

    // 根据地址查找联系人
    @GetMapping("/searchByAddress/{address}")
    public List<Friend> searchFriendsByAddress(@PathVariable String address) {
        String sql = "SELECT * FROM fr WHERE address=?";
        return jdbcTemplate.query(sql, new Object[]{address}, (rs, rowNum) ->
                new Friend(rs.getString("name"), rs.getString("phone_number"), rs.getString("address")));
    }
}
