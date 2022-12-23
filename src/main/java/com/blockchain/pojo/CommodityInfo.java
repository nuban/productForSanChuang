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
@ApiModel(value="CommodityInfo对象", description="")
public class CommodityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "commodity_name")
    private String commodityName;

    @TableField(value = "commodity_price")
    private String commodityPrice;

    @TableField(value = "by_address")
    private String byAddress;

    @TableField(value = "by_name")
    private String byName;

    private String information;

    private String images;

    @TableField(value = "create_time")
    private String createTime;


}
