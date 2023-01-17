package com.puuaru.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puuaru.cms.entity.CrmBanner;
import com.puuaru.cms.service.CrmBannerService;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-01-11
 */
@RestController
@RequestMapping("/cms")
@CrossOrigin
public class CrmBannerController {

    private final CrmBannerService crmBannerService;

    @Autowired
    public CrmBannerController(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }

    /**
     * 按id获取banner数据
     * @param crmId
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("按id获取banner数据")
    public CrmBanner getCrm(@PathVariable("id") Long crmId) {
        CrmBanner banner = crmBannerService.getById(crmId);
        return banner;
    }

    /**
     * 获取最后4个banner
     * @return
     */
    @GetMapping("")
    @ApiOperation("获取最后4个banner")
    @Cacheable(value = "index", key = "'banner'")
    public List<CrmBanner> getNewCrm() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 4");
        List<CrmBanner> result = crmBannerService.list(wrapper);
        return result;
    }

    /**
     * 分页查询banner数据
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/page/{current}/{size}")
    @ApiOperation("分页查询banner数据")
    public ResultCommon pageCrm(@PathVariable("current") Long current, @PathVariable("size") Long size) {
        Page<CrmBanner> bannerPage = crmBannerService.page(new Page<CrmBanner>(current, size));

        return ResultCommon.success()
                .setData("items", bannerPage)
                .setData("total", bannerPage.getTotal());
    }

    /**
     * 添加banner
     * @param crmBanner
     * @return
     */
    @PostMapping("")
    @ApiOperation("添加banner")
    public Boolean saveCrm(@RequestBody CrmBanner crmBanner) {
        boolean result = crmBannerService.save(crmBanner);
        return result;
    }

    /**
     * 按id删除banner
     * @param crmId
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("按id删除banner")
    public Boolean deleteCrm(@PathVariable("id") Long crmId) {
        boolean result = crmBannerService.removeById(crmId);
        return result;
    }

    /**
     * 按id修改banner信息
     * @param crmBanner
     * @return
     */
    @PutMapping("")
    @ApiOperation("按id修改banner信息")
    public Boolean updateCrm(@RequestBody CrmBanner crmBanner) {
        boolean result = crmBannerService.updateById(crmBanner);
        return result;
    }
}

