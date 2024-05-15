import {Header} from "./components/Header.jsx";

export default function Profile({logout, logged}){
    return (
        <>
            <Header pathList={[
                {id: 1,
                    onClick: logged,
                    pathname: "На главную"},
                {id: 2,
                    onClick: logout,
                    pathname: "Выйти"}
            ]}/>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad atque cumque dolore dolores ea eaque earum et excepturi fuga illum magni minima molestias natus, perferendis provident quas quidem, quis sequi?</p>
        </>
    )
}