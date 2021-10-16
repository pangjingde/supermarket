package com.southwind.mmall002.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Data
@Getter
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Cart implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer productId;

    private Integer quantity;

    private Float cost;

    private Integer userId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;



}
