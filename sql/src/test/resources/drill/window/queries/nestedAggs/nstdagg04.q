SELECT c2, AVG(MAX(c1)) OVER() FROM "tblWnulls.parquet" GROUP BY c2
