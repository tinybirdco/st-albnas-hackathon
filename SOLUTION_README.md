# Tinybird "St. Albnas" Hackathon

Solution

1. Install `duckdb`. The following code will download the `duckdb` CLI in the current directory:

```bash
get_duckdb() {
    latest_version=$(curl -sL https://api.github.com/repos/duckdb/duckdb/releases/latest | jq -r ".tag_name")
    echo "$latest_version"
    if [[ $(uname) == 'Darwin' ]]; then
        curl -sSLO https://github.com/duckdb/duckdb/releases/download/"$latest_version"/duckdb_cli-osx-universal.zip
        # -o = overwrite
        unzip -o duckdb_cli-osx-universal.zip
        rm duckdb_cli-osx-universal.zip
    else
        curl -sSLO https://github.com/duckdb/duckdb/releases/download/"$latest_version"/duckdb_cli-linux-amd64.zip
        # -o = overwrite
        unzip -o duckdb_cli-linux-amd64.zip
        rm duckdb_cli-linux-amd64.zip
    fi;
}

get_duckdb
```

2. Run the SQL query using `duckdb`:

```bash
./duckdb < query.sql
```

3. The query will output the accuracy and save the results to `output.csv`.
