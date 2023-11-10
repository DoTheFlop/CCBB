import styled from "styled-components";

export const Main = styled.main`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
`;

export const Head = styled.header`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  width: 85%;
`;

export const Headtop = styled.header`
  display: flex;
  align-items: center;
`;

export const Menuhead = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 10%;
  padding-right: 10%;
  width: 80%;
  border-bottom: 1px solid #ccc;
`;

export const Votebodycover = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  background-color: white;
  padding-bottom: 5vh;
`;

export const Headbottom = styled.div`
  padding-left: 4%;
  h3 {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
`;
export const HeadLeft = styled.div``;

export const HeadRight = styled.div``;

export const DetailBody = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 5vh;
  flex-direction: column;
  white-space: pre-line;
`;

export const Moviebody = styled.div`
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Votebody = styled.div`
  display: flex;
  flex-direction: column;
  margin: 2% 10%;
  font-size: large;
  padding-bottom: 20px;
  white-space: pre-line;
`;

export const VoteBodybot = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  p {
    font-weight: bold;
  }
`;

export const Votebutton = styled.div`
  color: black;
  display: flex;
  margin-top: 10px;
`;

export const ImgVS = styled.img`
  margin: 0 50px;
`;
export const Imgward = styled.img``;

export const ImgTier = styled.img`
  padding-left: 10px;
`;

export const ProfileBox = styled.div`
  display: flex;
  background-color: ${(props) => props.$bgcolor};
  border-radius: 8px;
  padding: 5px 10px;
`;

export const ArticleMenu = styled.div`
  margin-top: 15%;
`;

export const PromiseP = styled.div`
  display: flex;
  align-items: center;

  & > h3 {
    cursor: pointer;
  }

  & > svg {
    cursor: pointer;
  }
`;

export const PromisePageWrapper = styled.div`
  display: ${(props) => (props.$opened ? "block" : "none")};
  flex-direction: column;
`;

export const BodyBottom = styled.div`
  border-top: 1px solid #ccc;
  margin: 0 10%;
  h4 {
    margin-top: 0;
  }
`;

export const Createcomment = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  align-items: center;
  textarea {
    flex: 1;
  }
`;

export const CommentBody = styled.div``;

export const VoteBodyButtonBox = styled.div`
  display: flex;
  justify-content: space-between;
  width: 320px;
`;
