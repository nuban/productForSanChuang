package com.blockchain.controller;

        import com.blockchain.pojo.CommodityInfo;
        import com.blockchain.pojo.ResBean;
        import com.blockchain.pojo.Tempname;
        import com.blockchain.service.ICommodityInfoService;
        import com.blockchain.service.ITempnameService;
        import com.blockchain.service.IUserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import java.sql.Time;
        import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/tempname")
public class TempnameController {

    @Autowired
    private ITempnameService iTempnameService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICommodityInfoService iCommodityInfoService;


    @GetMapping("/findall")
    public ResBean findAll() {
        return iTempnameService.findall();
    }

    @DeleteMapping("/delete/{id}")
    public ResBean deleteByid(@PathVariable Integer id) {

        if (iTempnameService.deleteByid(id) == 1) {
            //iCommodityInfoService.save(commodityInfo);
            return ResBean.success("删除成功");
        } else {
            return ResBean.error("删除失败");
        }

    }

    @PostMapping("/save")
    public ResBean save(@RequestBody Tempname tempname){

        return iTempnameService.add(tempname);
    }
}
