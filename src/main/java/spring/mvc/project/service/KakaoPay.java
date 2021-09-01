package spring.mvc.project.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//import lombok.extern.java.Log;
@Service
public class KakaoPay {

	 
    private static final String HOST = "https://kapi.kakao.com";
    
    //private KakaoPayReadyVO kakaoPayReadyVO;
    
    public String kakaoPayReady() {
 
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "admin key를 넣어주세요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");//가맹점 코드 TC0ONETIME
        params.add("partner_order_id", "1001");//주문번호 
        params.add("partner_user_id", "gorany");//회원 id
        params.add("item_name", "갤럭시S9");	//상품명
        params.add("quantity", "1");		//상품수량
        params.add("total_amount", "2100"); //상품 총액
        params.add("tax_free_amount", "100"); //상품 비과세 금액
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");//성공시url
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");//취소시url
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");//결재 실패시 url
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            //kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            //return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } /*catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        return "/pay";
        
    }
}
