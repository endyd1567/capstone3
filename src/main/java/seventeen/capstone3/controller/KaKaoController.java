package seventeen.capstone3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import seventeen.capstone3.domain.Member;
import seventeen.capstone3.model.KakaoProfile;
import seventeen.capstone3.repository.MemberRepository;
import seventeen.capstone3.service.KaKaoService;
import seventeen.capstone3.service.MemberService;

@Controller
@RequestMapping("/auth/kakao")
public class KaKaoController {

    @Autowired KaKaoService ks;
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired KaKaoService kaKaoService;

    @GetMapping("/logout")
    public String logoutPage() {
        return "home";
    }  // 로그아웃된 홈 화면


    @GetMapping("/callback")
    public String kakaoCallback(String code, Model model) {

        //토큰 발급
        String accessToken = kaKaoService.getAccessToken(code);

        // 사용자 정보
        KakaoProfile kakaoProfile = kaKaoService.getUserInfo(accessToken);

        //먼저 정보가 저장되있는지 확인하는 코드.
        Member findMember = memberRepository.findOne(kakaoProfile.getId());
        if(findMember==null){
            // findMember가 null이면 정보가 저장이 안되있는것이므로 정보를 저장.
            Member member = new Member();
            member.setId(kakaoProfile.getId());
            member.setName(kakaoProfile.getProperties().getNickname());
            memberService.join(member);
        }
        Member member = memberRepository.findOne(kakaoProfile.getId());
        model.addAttribute("member",member);
        return "memberHome";  // 로그인을 성공한 회원 메인 페이지 이동
    }

}