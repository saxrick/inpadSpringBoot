export default function Help() {
    return (
        <>
            <h1 className="title">
                Столкнулись с проблемой?
            </h1>
            <h2 className="subtitle">
                Подробно опишите ситуацию, и мы постараемся вам помочь
            </h2>
            <form className="formUpdate">
                <div className="inpVal">

                    <input type="text" id="modelName" name="modelName" className="form-control"
                           placeholder="Тема обращения"
                    />
                </div>
                <div className="inpVal">
                    <input type="text" id="modelInfo" name="modelInfo" className="form-control"
                           placeholder="Напишите ваш вопрос"
                    />
                </div>
                <div className="middle-button">
                    <button type="button"
                            className="btn btn-outline-dark btn-block mb-4">Отправить
                    </button>
                </div>
            </form>
        </>
)
}