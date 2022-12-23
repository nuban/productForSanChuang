package com.blockchain.controller;

        import com.blockchain.pojo.CommodityInfo;
        import com.blockchain.pojo.ResBean;
        import com.blockchain.pojo.Tempname;
        import com.blockchain.service.ICommodityInfoService;
        import com.blockchain.service.IUserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

        import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/commodity-info")
public class CommodityInfoController {

    @Autowired
    private ICommodityInfoService iCommodityInfoService;


    @PostMapping("/save")
    public ResBean save(@RequestBody Tempname tempname, HttpServletRequest request){
        return iCommodityInfoService.save(tempname,request);
    }
    @PostMapping("/add")
    public ResBean add(@RequestBody CommodityInfo commodityInfo, HttpServletRequest request){
        return iCommodityInfoService.add(commodityInfo,request);
    }

    @DeleteMapping("/delete/{id}")
    public ResBean delete(@PathVariable Integer id, HttpServletRequest request){
        return iCommodityInfoService.deleteByid(id,request);
    }
    @GetMapping("/findAll")
    public ResBean findAll(){
        return iCommodityInfoService.findAll();
    }
    @PostMapping("/upload")
    public ResBean saveForm(@RequestParam("image")MultipartFile multipartFile ,
                            @RequestParam("commodityName") String commodityName,
                            @RequestParam("commodityInformation") String commodityInformation,
                            @RequestParam("commodityPrice") String commodityPrice){
        return iCommodityInfoService.saveForm(multipartFile,commodityName,commodityInformation,commodityPrice);



    }



}
