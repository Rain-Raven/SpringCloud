package com.zxa.backgroud.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxa.backgroud.core.common.annotion.BussinessLog;
import com.zxa.backgroud.core.common.annotion.Permission;
import com.zxa.backgroud.core.common.constant.dictmap.DeptDict;
import com.zxa.backgroud.core.common.constant.factory.ConstantFactory;
import com.zxa.backgroud.core.common.node.TreeviewNode;
import com.zxa.backgroud.core.common.node.ZTreeNode;
import com.zxa.backgroud.core.common.page.LayuiPageFactory;
import com.zxa.backgroud.core.log.LogObjectHolder;
import com.zxa.backgroud.modular.system.entity.Category;
import com.zxa.backgroud.modular.system.entity.Dept;
import com.zxa.backgroud.modular.system.model.DeptDto;
import com.zxa.backgroud.modular.system.service.CategoryService;
import com.zxa.backgroud.modular.system.service.DeptService;
import com.zxa.backgroud.modular.system.warpper.DeptTreeWrapper;
import com.zxa.backgroud.modular.system.warpper.DeptWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 类别控制器
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    private String PREFIX = "/modular/system/category/";

    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转到部门管理首页
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "category.html";
    }

    /**
     * 跳转到添加部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/category_add")
    public String deptAdd() {
        return PREFIX + "category_add.html";
    }

    /**
     * 跳转到修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("/category_update")
    public String deptUpdate(@RequestParam("id") Long id) {

        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }

        //缓存部门修改前详细信息
        Category category = categoryService.getById(id);
        LogObjectHolder.me().set(category);

        return PREFIX + "category_edit.html";
    }


    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(Category category) {
        category.setId((long) new Random().nextInt(Integer.MAX_VALUE));
        this.categoryService.addCategory(category);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "deptId", required = false) String deptId) {
        Page<Map<String, Object>> list = this.categoryService.list(condition, deptId);
        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 部门详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{deptId}")
    @ResponseBody
    public Object detail(@PathVariable("deptId") Long deptId) {
        Category category = categoryService.getById(deptId);
        DeptDto deptDto = new DeptDto();
        BeanUtil.copyProperties(category, deptDto);
        deptDto.setPName(ConstantFactory.me().getDeptName(deptDto.getPid()));
        return deptDto;
    }

    /**
     * 修改部门
     *
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseData update(Category category) {
        categoryService.editCategory(category);
        return SUCCESS_TIP;
    }

    /**
     * 删除类别
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResponseData delete(@RequestParam Long id) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(id));

        categoryService.removeById(id);

        return SUCCESS_TIP;
    }
}

