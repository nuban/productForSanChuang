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
@ApiModel(value="ShoppingCart对象", description="")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "commodity_id")
    private Integer commodityId;

    @TableField(value = "cmd_name")
    private String cmdName;

    @TableField(value = "cmd_price")
    private BigDecimal cmdPrice;

    @TableField(value ="cmd_address" )
    private String cmdAddress;

    @TableField(value = "new_address")
    private String newAddress;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "cmd_images")
    private String cmdImages;


}
