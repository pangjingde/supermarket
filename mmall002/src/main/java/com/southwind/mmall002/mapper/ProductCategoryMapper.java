package com.southwind.mmall002.mapper;

import com.southwind.mmall002.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.mmall002.productCategoryVO.ProductCategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {






}
