import { Spin } from "antd";
import { Suspense } from "react"

export const LoadingWrapper = ({ Component }) => {
    //component prop has to be a lazy loaded component...
    return (
        <Suspense fallback={<div style={{ height: "100vh", width: "100vw", display: "flex", alignItems: "center", justifyContent: "center" }}><Spin size="large" /></div>}>
            <Component />
        </Suspense>
    );
}