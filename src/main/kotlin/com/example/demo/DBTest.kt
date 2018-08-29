package com.example.demo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class DBTest(val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper<Task> { rs, _ ->
        Task(rs.getLong("id"),
                rs.getString("content"),
                rs.getBoolean("done"))

    }

    @RequestMapping("/dbtest")
    fun select(model: Model):String {
        val sql = "select * from task"
        val rs = jdbcTemplate.query(sql, rowMapper)
        //System.out.println(rs.get(0).id)
        model.addAttribute("rs", rs)
        return "dbtest"
    }

    @RequestMapping("/dbinsert")
    fun insert(@RequestParam("content") content: String,
            model: Model): String{
        val sql = "insert into TASK(content) values(?)"
        val num = jdbcTemplate.update(sql, content)
        System.out.print(num)
        select(model)
        return "dbtest"
    }
}