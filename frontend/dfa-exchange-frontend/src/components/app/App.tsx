import './App.css'
import NavBar from "../navbar/NavBar.tsx";
import CardStock from "../card_stock/CardStock.tsx";
import Avatar from "../avatar/Avatar.tsx";
import Menu from "../menu/Menu.tsx";

function App() {
    return (
        <div className="app">
            <NavBar/>
            <div className="container">
                <div className="content">
                    <div className="flex">
                        <div className="flex-1">
                            <Avatar/>
                            <Menu/>
                        </div>
                        <div className="flex-3">
                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={true}
                            />

                            <CardStock
                                logo_url="https://www.nvidia.com/content/dam/en-zz/Solutions/about-nvidia/logo-and-brand/02-nvidia-logo-color-grn-500x200-4c25-p@2x.png"
                                company_title="NVIDIA"
                                stock_count={4}
                                stock_price={78.1}
                                total_price={4 * 78.1}
                                delta_price={16.17}
                                delta_price_percent={4.92}
                                is_up_stock={false}
                            />
                        </div>
                    </div>

                </div>
            </div>
        </div>

    )

}

export default App
