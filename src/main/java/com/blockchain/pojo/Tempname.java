package com.blockchain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tempname对象", description="")
public class Tempname implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "commodity_name")
    private String commodityName;

    @TableField(value = "commodity_price")
    private String commodityPrice;

    @TableField(value = "commodity_information")
    private String commodityInformation;

    @TableField(value = "commodity_images")
    private String commodityImages;

    @TableField("create_time")
    private String createTime;

    @TableField("user_id")
    private Integer userId;


}
