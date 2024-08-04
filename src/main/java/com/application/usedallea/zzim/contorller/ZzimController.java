package com.application.usedallea.zzim.contorller;

import com.application.usedallea.product.dto.ProductDTO;
import com.application.usedallea.zzim.dao.ZzimDAO;
import com.application.usedallea.zzim.dto.ZzimDTO;
import com.application.usedallea.zzim.service.ZzimService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zzim")
public class ZzimController {


    @Autowired
    private ZzimService zzimService;

    private String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return  (String) session.getAttribute("userId");
    }

    @PostMapping("/check")
        @ResponseBody
        public Map<String,Object> check(@RequestParam("productId") long productId, HttpServletRequest request) {
            Map<String, Object> response = new HashMap<>();
            String status = "y";
            int zzimCount = 0;  // 찜의 개수

            // 세션에서 사용자 아이디 가져오기
            String userId = getUserId(request);

            ZzimDTO zzimDTO = new ZzimDTO();
            zzimDTO.setProductId(productId);
            zzimDTO.setUserId(userId);

            //찜 정보 저장
          zzimService.insertZzim(zzimDTO);

            //해당 상품에 대한 찜이 있는지의 여부 확인
           boolean isalreadyZzim = zzimService.checkZzim(zzimDTO);
            if (isalreadyZzim) { // 이미 찜을 했다면
                zzimService.removeZzim(zzimDTO);  // 찜 삭제
                status="n";
            }

            // 찜 개수 가져오기
            zzimCount = zzimService.getZzimCount(zzimDTO.getProductId());
            response.put("status",status);
            response.put("zzimCount",zzimCount);

            return response;
        }


}
