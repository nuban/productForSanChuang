package com.bc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ws
 * @since 2022-02-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class CommodityInfo implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String commodityName;

    private BigDecimal commodityPrice;

    private String byAddress;

    private String byName;

    private String information;

    private String category;

    private String images;

    private String createTime;

    private String state;

    private BigDecimal auction;

    private Integer auctionid;

}
