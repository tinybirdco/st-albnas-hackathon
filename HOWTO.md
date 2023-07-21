README.md

- Create a python enviroment in version 3.10.10 and install the requirements.
  For example:
    ```bash
    pyenv virtualenv 3.10.10 hackathon
    pyenv local hackathon
    pip install -r requirements.txt
    ```

- Execute the demo.
    ```bash
    python code.py
    ```

- The results:
    ```
    hackathon ❯ python code.py                                                                                                                                     (main|…4)

    Accuracy: 1.0

    Results:
    word                    [original, calculate]   jaro_winkler_distance   fixed
    St. Albans              [T, T]                  1.0                     St. Albans
    St.Albans               [T, T]                  1.0                     St. Albans
    St Albans               [T, T]                  0.97                    St. Albans
    St.Ablans               [T, T]                  0.98                    St. Albans
    St.albans               [T, T]                  1.0                     St. Albans
    St. Alans               [T, T]                  0.98                    St. Albans
    S. Albans               [T, T]                  0.97                    St. Albans
    St.. Albans             [T, T]                  0.98                    St. Albans
    S. Albnas               [T, T]                  0.93                    St. Albans
    St. Albnas              [T, T]                  0.98                    St. Albans
    St.Al bans              [T, T]                  1.0                     St. Albans
    St.Algans               [T, T]                  0.96                    St. Albans
    Sl.Albans               [T, T]                  0.9                     St. Albans
    St. Allbans             [T, T]                  0.98                    St. Albans
    St, Albans              [T, T]                  0.97                    St. Albans
    St. Alban               [T, T]                  0.98                    St. Albans
    St. Alban               [T, T]                  0.98                    St. Albans
    St. Paul                [F, F]                  0.83                    St. Paul
    Albans                  [F, F]                  0.89                    Albans
    AlbnaSt. Alberts        [F, F]                  0.63                    AlbnaSt. Alberts
    Alberta                 [F, F]                  0.67                    Alberta
    St. Johnsbury, VT       [F, F]                  0.75                    St. Johnsbury, VT
    Alban St.               [F, F]                  0.81                    Alban St.
    State of Alban          [F, F]                  0.79                    State of Alban
    Albany                  [F, F]                  0.8                     Albany
    Albania                 [F, F]                  0.76                    Albania
    Ban                     [F, F]                  0.0                     Ban
    Alfred                  [F, F]                  0.52                    Alfred
    St. Alfred              [F, F]                  0.82                    St. Alfred
    Saint                   [F, F]                  0.59                    Saint
    St                      [F, F]                  0.79                    St
    Alps                    [F, F]                  0.0                     Alps
    Alloy                   [F, F]                  0.44                    Alloy
    Alban Street            [F, F]                  0.74                    Alban Street
    AL                      [F, F]                  0.0                     AL
    Alabama                 [F, F]                  0.67                    Alabama
    Alexa                   [F, F]                  0.47                    Alexa
    Alfonso Soriano         [F, F]                  0.6                     Alfonso Soriano
    Snabla                  [F, F]                  0.76                    Snabla
    Peoria                  [F, F]                  0.43                    Peoria
    I ran out of ideas      [F, F]                  0.46                    I ran out of ideas
    ```

   