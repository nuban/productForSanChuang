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
 * @since 2022-02-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ShoppingCart implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer commodityId;

    private String cmdName;

    private BigDecimal cmdPrice;

    private String cmdAddress;

    private String newAddress;

    private Integer userId;

    private String cmdImages;

    private String createTime;


}
