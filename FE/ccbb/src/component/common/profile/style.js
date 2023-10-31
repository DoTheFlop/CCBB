import styled from "styled-components";

export const Profile = styled.div`
  display: flex;
  align-items: center;
  width: fit-content;
  flex-direction: ${(props) =>
    props.direction === "reverse" ? "row-reverse" : "row"};
`;

export const Name = styled.div`
  color: ${(props) => props.color || 'white'};
  padding-left: 10px;
  font-weight: bold;
  padding-right: 10px;
  font-size: ${(props) => `${props.size / 2}px`};
`;

export const Img = styled.img`
  border-radius: 50%;
`;
