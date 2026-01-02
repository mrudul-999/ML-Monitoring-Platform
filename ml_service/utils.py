import pandas as pd
import numpy as np
from influxdb_client import InfluxDBClient
from sklearn.preprocessing import StandardScaler

# InfluxDB config
INFLUX_URL = "http://localhost:8086"
INFLUX_TOKEN = "your-token"
INFLUX_ORG = "your-org"
INFLUX_BUCKET = "metrics_bucket"


def get_influx_client():
    return InfluxDBClient(
        url=INFLUX_URL,
        token=INFLUX_TOKEN,
        org=INFLUX_ORG
    )


def fetch_metrics(hours=24):
    client = get_influx_client()
    query_api = client.query_api()

    query = f'''
    from(bucket:"{INFLUX_BUCKET}")
      |> range(start: -{hours}h)
    '''

    tables = query_api.query(query)
    data = []

    for table in tables:
        for record in table.records:
            data.append(record.values)

    df = pd.DataFrame(data)

    if df.empty:
        return df

    df = df[['time', 'CPU', 'Memory', 'Latency', 'ErrorRate']]
    df.set_index('time', inplace=True)
    return df


def scale_metrics(df):
    scaler = StandardScaler()
    scaled = scaler.fit_transform(df)
    return scaled


def create_sliding_windows(data, window_size=5):
    X = []
    for i in range(len(data) - window_size):
        X.append(data[i:i + window_size])
    return np.array(X)
