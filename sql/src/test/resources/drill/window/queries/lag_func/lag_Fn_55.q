SELECT c0 , LAG(c0) OVER( PARTITION BY c2 ORDER BY c1 ) LAG_c0 FROM ( SELECT col0 c0, col1 c1, col2 c2 FROM "fewRowsAllData.parquet") sub_query