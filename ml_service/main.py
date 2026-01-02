from fastapi import FastAPI, Body
from utils import fetch_metrics, scale_metrics, create_sliding_windows

app = FastAPI()


@app.get("/")
def health_check():
    return {"message": "ML service is running"}


@app.get("/train-data-preview")
def train_data_preview():
    df = fetch_metrics(hours=24)

    if df.empty:
        return {"message": "No data available"}

    scaled = scale_metrics(df)
    X = create_sliding_windows(scaled)

    return {
        "rows": len(df),
        "windows": len(X),
        "window_shape": X.shape
    }


@app.post("/detect_anomaly")
def detect_anomaly(metrics: dict = Body(...)):
    # ML model will be plugged here later
    return {
        "isAnomaly": False,
        "received": metrics
    }
