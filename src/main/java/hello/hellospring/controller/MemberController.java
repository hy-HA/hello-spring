package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

     @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
     }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        //member.getName()확인
        //System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        //memberService.findMembers();에서 옵션+cmd+v
        List<Member> members = memberService.findMembers();
        // "members"만 치면 attributeName 자동 생성됨.
        // 멤버 리스트 자체를 모델에 담아서 뷰 템플에 넘길 것.
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
