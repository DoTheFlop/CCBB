import styled from "styled-components";

export const Main = styled.main`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    .input-file-button{
        padding: 8px 3px 6px 12px;
        background-color:#1F2A70;
        border-radius: 4px;
        color: white;
        cursor: pointer;
        width: 50px;
        margin-left: 93%;
        margin-top: 1%;
        font-weight: bold;
    }
`
export const Head = styled.header`
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    width: 85%;
    
    
`
export const Headtop = styled.header`
    background-color: #0B123F;
    margin-bottom: 20px;
`

export const CreateBodyCover = styled.div`
    background-color: white;
    width: 100%;
    max-width: 100%;
    display: flex;
    justify-content: center;
    box-sizing: border-box;
    padding: 0 15%;

`

export const CreateBody = styled.div`
    width: 85%;
    display: flex;
    flex-direction: column;
    align-self: center;
    /* padding-left: 25%; */
    padding-bottom: 3%;
    margin-top: 3%;
`

export const Uploadfile = styled.div`
    width: 100%;
    border: 2px solid #ccc;
    height: 162px;
    /* padding: 0 0 10px 0; */
    border-radius: 5px;
    .delete-button {
        position: absolute;
        /* background-color: red; */
        color: red;
        border: none;
        border-radius: 50%;
        width: 20px;
        height: 20px;
        font-size: 18px;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
    }

    /* Style the delete button when hovering over it */
    .delete-button:hover {
        background-color: darkred;
    }
    /* Style the container's scrollbar */
    div::-webkit-scrollbar {
    width: 12px; /* Set the width of the scrollbar */
    }

    /* Style the scrollbar track (the area behind the thumb) */
    div::-webkit-scrollbar-track {
    background: #f1f1f1;
    }

    /* Style the scrollbar thumb (the draggable part) */
    div::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 6px;
    }

    /* Change scrollbar thumb color on hover */
    div::-webkit-scrollbar-thumb:hover {
    background: #555;
    }
`

export const CreateBodybottom = styled.div`
    display: flex;
    justify-content: space-between;
    
`

export const LeftBottom = styled.div`
    width: 80%;
    display: flex;
    flex-direction: column;
    font-size: 20px;
    font-weight: bold;

    .Mui-focused .MuiOutlinedInput-notchedOutline {
        border: 2px solid black !important;  

    }

`

export const RightBottom = styled.div`
    width: 70%;
    display: flex;
    flex-direction: column;
    
    .react-datepicker {
        font-family: Arial, sans-serif;
    }

    .react-datepicker__input-container {
    display: inline-block;
    width: 100%;
    }

    .react-datepicker__input-container input {
    width: 125%;
    padding: 10px;
    border-radius: 5px;
    font-size: 16px;
    }

    .react-datepicker__day--selected {
    background-color: #007bff;
    color: white;
    }
`

export const buttonBox = styled.div`
    display: flex;
    justify-content: end;
    width: 100%;
    
`

export const VoteRange = styled.div`
    display: flex;
    flex-direction: column;
    font-size:20px;
    font-weight: bold;
    width: 75%;
    margin-bottom: 2%;
    position: relative;
    
`

export const UserWrapper = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
`