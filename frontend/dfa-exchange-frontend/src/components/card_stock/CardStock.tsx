import './CardStock.css'

interface Props {
    logo_url: string;
    company_title: string;
    stock_count: number;
    stock_price: number;
    total_price: number;
    delta_price: number;
    delta_price_percent: number;
    is_up_stock: boolean;
}
function CardStock(props: Props) {

    return (
        <div className="card_stock">
            <div className="logo flex-1">
                <img alt="" src={props.logo_url} className="card_stock_company_logo"></img>
            </div>
            <div className="card_stock_info flex-10">
                <div className="card_stock_info_price_count">
                    <h4>{props.company_title}</h4>
                    <p className="price_and_count">
                        <p className="count">{props.stock_count} шт.</p>
                        <p>·</p>
                        <p className="price">{props.stock_price} ₽</p>
                    </p>

                </div>

                <div className="card_stock_info_price_count text-align_right">
                    <h4>{props.total_price} ₽</h4>
                    <p className={props.is_up_stock ? `price_and_count price_and_count__green` : "price_and_count price_and_count__red"}>
                        <p>{props.delta_price} ₽ </p>
                        <p className="price">({props.delta_price_percent} %)</p>
                    </p>
                </div>

            </div>
        </div>
    );
}

export default CardStock;