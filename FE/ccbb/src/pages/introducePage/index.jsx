import * as S from "./style";
import { IntroduceWrapper, Title, Description, SubTitle, Text } from "./style";
import Headermenu from "../../component/common/headers/headermenu";

export default function IntroducePage() {
  return (
    <S.main>
      <Headermenu title="사이트 소개">
      </Headermenu>
    <IntroduceWrapper>
      <Title>CC.BB 사이트에 오신 것을 환영합니다!</Title>
      <Description>
        CC.BB는 리그오브레전드 게임 내에서 발생하는 분쟁의 우열을 판단하기 위한 플랫폼입니다.<br/><br/>
        여러분이 원하는 것이 공평한 게임을 즐기는 것이라면, 여기가 정확히 필요한 곳입니다.
      </Description>
      <SubTitle>우리의 목표</SubTitle>
      <Text>
        우리의 목표는 게임 내에서의 분쟁을 공정하게 해결하는 것입니다. <br/><br/>
        당사자들의 영상과 논점을 기반으로, 누가 맞는지 투표를 통해 판단합니다.
      </Text>
      <SubTitle>누가 투표할 수 있나요?</SubTitle>
      <Text>
        투표는 당사자들이 설정한 티어 이상의 사람들만 할 수 있습니다. <br/><br/>
        이를 통해 투표의 공정성을 보장하며, 투표 결과에 따라 진 당사자는 공약을 이행해야 합니다.
      </Text>
      <SubTitle>어떻게 시작하나요?</SubTitle>
      <Text>
        시작하기는 매우 간단합니다. 로그인 후, 분쟁을 제출하거나 기존의 분쟁에 투표를 할 수 있습니다.<br/><br/>
        대신 투표에서 질 경우 공약은 각오하셔야 겠지만요 ^^ 
      </Text>
    </IntroduceWrapper>
    </S.main>
  );
}
