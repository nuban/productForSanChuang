package com.blockchain.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@ApiModel(value="OrderInfo对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "commodity_name")
    private String commodityName;

    @TableField(value = "commodity_id")
    private Integer commodityId;

    @TableField(value = "commodity_price")
    private BigDecimal commodityPrice;

    @TableField(value = "commodity_images")
    private String commodityImages;

    @TableField(value = "commodity_info")
    private String commodityInfo;

    @TableField(value = "seller_add")
    private String sellerAdd;

    @TableField(value = "buyer_id")
    private Integer buyerId;

    @TableField(value = "buyer_add")
    private String buyerAdd;

    @TableField(value = "creat_time")
    private String creatTime;


}
