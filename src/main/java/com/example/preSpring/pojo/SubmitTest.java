package com.example.preSpring.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小叶子
 */
@TableName("t_submit_test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTest {
    @TableId(type = IdType.AUTO, value = "id")
    private long id;
    private String testNum;
    private String studentNum;
    private String submitContext;
    private String testWord;

}
