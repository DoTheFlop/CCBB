import styled from "styled-components";

export const Main = styled.main`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: white;
`
export const Head = styled.header`
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    width: 100%;
    
    
`
export const Headtop = styled.header`
    background-color: #0B123F;
    margin-bottom: 20px;
`
export const Headbottom = styled.header`
    background-color: #154C61;
    border-top-right-radius: 10px;
    border-top-left-radius: 10px;
`
export const Menuhead = styled.div`
    display: flex;
    flex-direction: row;
    padding-left: 30px;

    h3 {
    cursor: pointer;
    color: #A4A4A4;
    padding-bottom: 5px;
    margin-right: 10px;
    }

    h3.active {
        color: white;
        border-bottom: 2px solid white; 
    }
`
export const Actions = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 100%;
    font-size: 17px;
`
export const A_1 = styled.div`
    margin-left: auto;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding-right: 30px;
`

export const Votebodycover = styled.div`
    display: flex;
    justify-content: center;
`
export const Votebody = styled.div`
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    background-color: #C6D8D0;
    justify-content: start;
`

export const Dropdown = styled.div`
    padding-left: 30px;
    .css-1xc3v61-indicatorContainer{
        color: white;
    }
    .css-1xc3v61-indicatorContainer:hover{
        color: white;
    }
    .css-15lsz6c-indicatorContainer{
        color: white;
    }
    .css-15lsz6c-indicatorContainer:hover{
        color: white;
    }
    
`

