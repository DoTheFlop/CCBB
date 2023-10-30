import styled from "styled-components";



export const Profile = styled.div`
  display: flex;
  align-items: center;
`;

export const Name = styled.div`
  color: ${(props) => props.color || 'white'};
  padding-left: 10px;
  font-size: 25px;
`;

export const Img = styled.img`
  border-radius: 50%;
`