package edu.hytc.moon.controller;

import edu.hytc.moon.domain.AnswerResult;
import edu.hytc.moon.domain.InfoEntity;
import edu.hytc.moon.domain.Readcomment;
import edu.hytc.moon.domain.Teacher;
import edu.hytc.moon.service.HomeworkanswerService;
import edu.hytc.moon.service.InfoService;
import edu.hytc.moon.vo.InfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/info")
public class InfoController {


    @Autowired
    private InfoService infoService;


    //跳转到新增
    @RequestMapping("/findAllInfo")
    public String doAddHomeWork( Model model, HttpServletRequest request){
        List<InfoEntity> infoEntities  = infoService.findALlInfos();
        model.addAttribute("infos",infoEntities);
        return "/info/infoList";
    }
    //跳转到新增
    @RequestMapping("/findInfoByCondition")
    public String findInfoByCondition( Model model, HttpServletRequest request, InfoVo infoVo){
        List<InfoEntity> infoEntities  = infoService.findInfoByCondition(infoVo);
        model.addAttribute("infos",infoEntities);
        return "/info/infoList";
    }


    //获取所有记录
    @RequestMapping("/deleteInfo/{id}")
    public String deleteRecore(HttpServletRequest request,@PathVariable("id") Integer infoId){

        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");
        infoService.deleteInfo(infoId,teacher.getTeacherId());

        return "redirect:/info/findAllInfo/";
    }




    @RequestMapping("/addInfo")
    public String editInfo(HttpServletRequest request, Model model, InfoVo infoVo){

        InfoEntity infoEntity = new InfoEntity();

        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");


        BeanUtils.copyProperties(infoVo,infoEntity);

        infoEntity.setCreateUse(teacher.getTeacherId());
        infoEntity.setUpdateUser(teacher.getTeacherId());

        infoService.saveInfoEntiy(infoEntity);

        return "redirect:/info/findAllInfo";
    }

    @RequestMapping("/toaddInfo")
    public String toaddInfo(HttpServletRequest request, Model model ){
        return "/info/addInfo";
    }


    @RequestMapping("/toeditInfo/{id}")
    public String toeditInfo(HttpServletRequest request, Model model,@PathVariable("id") Integer infoId){

        InfoEntity infoEntity =  infoService.finInfoById(infoId);

        model.addAttribute("info",infoEntity);
        return "/info/editInfo";
    }

    @RequestMapping("/editInfo")
    public String editInfo( Model model,  InfoVo infoVo,HttpServletRequest request){

        InfoEntity infoEntity  = new InfoEntity();

        HttpSession session=request.getSession();

        Teacher teacher = (Teacher)session.getAttribute("logerd");

        BeanUtils.copyProperties(infoVo,infoEntity);

        infoEntity.setCreateUse(teacher.getTeacherId());
        infoEntity.setUpdateUser(teacher.getTeacherId());
        infoEntity.setId(Integer.parseInt(infoVo.getId()));

        infoService.updateEntity(infoEntity);

        return "redirect:/info/findAllInfo/";
    }


}
