package com.bc.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ws
 * @since 2022-02-27
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class OrderInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String commodityName;

    private Integer commodityId;

    private BigDecimal commodityPrice;

    private String commodityImages;

    private String commodityInfo;

    private String sellerAdd;

    private Integer buyerId;

    private String buyerAdd;

    private String creatTime;

    private String orderHash;


}
