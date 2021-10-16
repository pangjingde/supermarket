package com.southwind.mmall002.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall002.entity.Product;
import com.southwind.mmall002.entity.ProductCategory;
import com.southwind.mmall002.mapper.ProductCategoryMapper;
import com.southwind.mmall002.mapper.ProductMapper;
import com.southwind.mmall002.productCategoryVO.ProductCategoryVO;
import com.southwind.mmall002.productCategoryVO.ProductVO;
import com.southwind.mmall002.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.southwind.mmall002.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {


    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {


        //一级分类
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("type",1);
        List<ProductCategory> levelOne=productCategoryMapper.selectList(wrapper);

        List<ProductCategoryVO> levelOneVO = levelOne.stream().map(e -> new ProductCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
        //图片赋值
        //商品信息赋值
        for (int i = 0; i < levelOneVO.size(); i++) {
            levelOneVO.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVO.get(i).setTopImg("/images/top"+i+".png");

            wrapper=new QueryWrapper();
            wrapper.eq("categorylevelone_id",levelOne.get(i).getId());
            List<Product> productList = productMapper.selectList(wrapper);

            List<ProductVO> productVOList=productList.stream().map(e->new ProductVO(e.getId(),e.getName(),e.getPrice(),e.getFileName())).collect(Collectors.toList());
            levelOneVO.get(i).setProductVOList(productVOList);

        }
        for (ProductCategoryVO levelOneProductCategoryVO : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("type",2);
            wrapper.eq("parent_id",levelOneProductCategoryVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProductCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());

            levelOneProductCategoryVO.setChildren(levelTwoVO);

            for (ProductCategoryVO levelTwoProductCategoryVO : levelTwoVO) {
              wrapper=new QueryWrapper();
              wrapper.eq("type",3);
              wrapper.eq("parent_id",levelTwoProductCategoryVO.getId());
                List<ProductCategory> leveThree= productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> leveThreeVo=leveThree.stream().map( e ->new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                levelTwoProductCategoryVO.setChildren(leveThreeVo);
            }
        }


        return levelOneVO;
    }
}
