package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/24 22:56
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.exception.myException;
import com.example.bookshop.pojo.Type;
import com.example.bookshop.service.typeService;
import com.example.bookshop.util.RedisUtil;
import com.example.bookshop.vo.TypeVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.bookshop.constants.CommonConstant.*;


/**
 * @ClassName:typeServiceImpl
 * @Auther: shu'ai
 * @Description: 分类的操作
 * @Date: 2021/10/24 22:56
 * @Version: v1.0
 */
@Service
public class typeServiceImpl extends ServiceImpl<TypeDao, Type> implements typeService {

    @Autowired
    TypeDao typeDao;
    @Autowired
    RedisUtil redisUtil;

    Logger logger = LogManager.getLogger(typeServiceImpl.class);

    @Transactional
    @Override
    public void add(Type type) {
        Type type1 = typeDao.selectOne(
                new LambdaQueryWrapper<Type>()
                .select(Type::getTypename,Type::getLevel,Type::getParentId,Type::getId)
                .eq(Type::getTypename,type.getTypename())
        );
        if(type1!=null) throw new myException("该分类名已存在");
        int level = type.getLevel();
        switch (level){
            case 1: redisUtil.lSet(TYPE1,type);break;
            case 2: redisUtil.lSet(TYPE2,type);break;
            case 3: redisUtil.lSet(TYPE3,type);break;
            default:throw new myException("数据异常");
        }
        typeDao.insert(type);
        logger.info("新增分类成功--"+type.toString());
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Type type =  typeDao.selectOne(
                new LambdaQueryWrapper<Type>()
                .select(Type::getTypename,Type::getLevel,Type::getParentId,Type::getId)
                .eq(Type::getParentId,id)
        );
        if(type!=null) throw new myException("该分类存在子分类，无法删除");
        List<Integer> books = typeDao.selectBookByType(id);
        if(books.size()!=0) throw new myException("该分类下存在图书，无法删除");
        int level = type.getLevel();
        switch (level){
            case 1: redisUtil.lRemove(TYPE1,1,type);break;
            case 2: redisUtil.lRemove(TYPE2,1,type);break;
            case 3: redisUtil.lRemove(TYPE3,1,type);break;
            default:throw new myException("数据异常");
        }
        typeDao.deleteById(id);
        logger.info("图书分类删除成功");
    }

    @Override
    public List<Type> getByLevel(Integer level) {
        List<Object> lists = null;
       switch (level){
           case 1: lists = redisUtil.lGet(TYPE1,0,-1);break;
           case 2: lists = redisUtil.lGet(TYPE2,0,-1);break;
           case 3: lists = redisUtil.lGet(TYPE3,0,-1);break;
       }
//       logger.info("==="+lists.toString());
       return Object2Type(lists);
    }


    @Override
    public List<Type> getChildType(String typename){
        if(redisUtil.hasKey(typename)) return Object2Type(redisUtil.lGet(typename,0,-1));
        List<Type> lists = null;
        int level = typeDao.getLevel(typename);
        if(level!=ONEI&&level!=TWOI) throw new myException("只有一二级分类用到该方法");
        lists = typeDao.getChildType(typename);
        redisUtil.lSet(typename,lists);
        logger.info("缓存写入---key="+typename);
        return lists;
    }

    @Override
    public List<TypeVO> getAllType() {
        List<Type> list = typeDao.selectList(
                new LambdaQueryWrapper<Type>()
                .select(Type::getTypename,Type::getId,Type::getLevel,Type::getParentId)
                .orderByDesc(Type::getLevel)
        );
        List<Type>  lists1 = this.getByLevel(ONEI);
        List<Type>  lists2 = this.getByLevel(TWOI);
        List<Type>  lists3 = this.getByLevel(THREEI);

//        List<List<List<TypeVO>>>

        List<TypeVO> type3list = new ArrayList<>();  //分类三集合
        for(Type t : lists3){
            TypeVO typeVO = TypeVO.builder()
                    .children(null)
                    .label(t.getTypename())
                    .id(t.getId())
                    .parent_id(t.getParentId())
                    .build();
            type3list.add(typeVO);
        }
        List<TypeVO> type2list =  new ArrayList<>();
        for(Type t : lists2){
            List<TypeVO> listt =  new ArrayList<>();
            Iterator it = type3list.iterator();
                while (it.hasNext()){
                    TypeVO tt = (TypeVO)it.next();
                    if(tt.getParent_id()==t.getId()){
                        listt.add(tt);
                        it.remove();
                    }
                }
                TypeVO typeVO = TypeVO.builder()
                        .parent_id(t.getParentId())
                        .id(t.getId())
                        .label(t.getTypename())
                        .children(listt)
                        .build();
                type2list.add(typeVO);
            }
        List<TypeVO> type1list =  new ArrayList<>();
        for(Type t:lists1){
            List<TypeVO> listt =  new ArrayList<>();
            Iterator it = type2list.iterator();
            while (it.hasNext()){
                TypeVO tt = (TypeVO) it.next();
                if(tt.getParent_id()==t.getId()){
                    listt.add(tt);
                    it.remove();
                }
            }
            TypeVO typeVO = TypeVO.builder()
                    .parent_id(t.getParentId())
                    .id(t.getId())
                    .label(t.getTypename())
                    .children(listt)
                    .build();
            type1list.add(typeVO);
        }
        return type1list;
        }




    /**
     * 把list2装入list1
     * @param
     * @param list2
     * @return
     */
    private  TypeVO add2List(Integer id,List<Type> list2){
        TypeVO typeVO = new TypeVO();
        List<TypeVO> typeVOList = new ArrayList<>();

        return typeVO;
    }

    /**
     * 强转object-->type 集合
     * @param lists
     * @return
     */
    private List<Type> Object2Type(List<Object> lists){
        List<Type> spuBos = lists.stream().map(spu -> {
            Type spuBo = new Type();
            //把spu拷贝到spuBo
            BeanUtils.copyProperties(spu, spuBo);
            return spuBo;
        }).collect(Collectors.toList());
        return spuBos;
    }
}
