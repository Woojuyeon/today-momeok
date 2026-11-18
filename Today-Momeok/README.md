# UserManager2
Sample project for DBP class
based on MVC architecture 

__Branches__
 
- master(v2.0)
- formCont(v2.1)
- community(v2.2)
- <span style="color:blue">rest(v2.3)</span> 

  
### rest branch
- branched from *community*
- add REST controllers and Ajax calls to them 
    * 커뮤니티 상세정보 조회 (Ajax GET)  
    * 커뮤니티 생성 (Ajax POST)
	

변경/추가된 코드

- controller.DispatcherServlet --  _REST 요청을 구별해서 관련 controller를 구하고 REST controller의 실행 결과에 대한 JSON 형식의 응답 메시지를 생성_
- controller.RequestMapping --  _두 가지 REST controller에 대한 request mapping 추가_
- controller.comm.ListCommunityController --  _community/listAndView.jsp를 뷰로 선택_
- controller.comm.rest.ViewCommunityRestController 추가 --  _GET 요청에 대해 특정 커뮤니티 정보 검색(결과는 DispatcherServlet에서 JSON 텍스트로 변환)_
- controller.comm.rest.CreateCommunityRestController 추가 --  _POST 요청으로 JSON 형식의 커뮤니티 정보를 입력 받아 커뮤니티 객체 생성 및 저장_
- controller.comm.CreateCommunityController 삭제
- community/list.jsp 삭제 및 community/listAndView.jsp 추가 --  _커뮤니티 리스트 출력 후 사용자가 선택한 커뮤니티의 상세 정보와 회원 리스트를 구해서 출력하기 위해 ViewCommunityRestController에 대한 Ajax GET 요청 실행_
- community/creationForm.jsp 수정 --  _form에 입력된 커뮤니티 정보를 전송하기 위해 CreateCommunityRestController에 대한 Ajax POST 요청 실행_ 


 
### 참고: 기존 Github repo에 추가된 새로운 branch를 local repo로 가져와서 import하는 방법
 
1. Eclipse에서 Git perspective로 전환
2. 기존 local repository 이름을 우클릭 > Remote > Fetch... 선택
2. 팝업 창이 열리면 Next 클릭
3. Source ref: 목록에서 가져올 branch 선택 후 Add Spec 클릭 > Finish 
4. local repository의 Branches > Remote Tracking 아래에 추가된 branch를 더블 클릭
5. Checkout as New Local Branch 클릭
6. Java EE perspective로 전환해서 import된 project에 대해 Maven > Update Project 실행
  
** 프로젝트에서 branch들 간의 전환은 Team > Switch To > branch 이름을 선택하거나 Git perspective에서 local branch 이름을 더블클릭하여 checkout 함 
