#include <bits/stdc++.h>
#include<iostream>
using namespace std;
#define up 1
#define left 2
#define diagonal 3
int trace[100][100]={0};

int editDistance(string x, string y, int m, int n){
    int table[m+1][n+1];
    for(int i=0; i<=m; i++){
        for(int j=0; j<=n; j++){
            if(i==0){
                table[i][j]=j;
                trace[i][j]=left;
            } 
            else if(j==0){
                table[i][j]=i;
                trace[i][j]=up;
            } 
            else if(x[i-1]==y[j-1]){
                table[i][j] = table[i-1][j-1];
                trace[i][j]=diagonal;
            }
            else if(x[i-1]!=y[j-1]){
                int q = min(table[i-1][j-1], min(table[i-1][j], table[i][j-1]));
                table[i][j]=q+1;
                if(q==table[i-1][j-1]) trace[i][j]=diagonal;
                else if(q==table[i][j-1]) trace[i][j]=left;
                else if(q==table[i-1][j]) trace[i][j]=up;
            }
        }
    }

    return table[m][n];
}


void printSequence(string x, string y, int i, int j){
    if(i==0 && j==0)
        return;
    else if(trace[i][j]==diagonal && x[i-1] == y[j-1]){
        printSequence(x,y,i-1,j-1);
    }
    else if(trace[i][j]==diagonal){
        printSequence(x,y,i-1,j-1);
        cout <<"Replace: "<< x[i-1] << " by " << y[j-1] << endl;           
    }
    else if(trace[i][j]==left){
        printSequence(x,y,i,j-1);
        cout<<"insert: "<<y[j-1]<<endl;
    }
    else if(trace[i][j]==up){
        printSequence(x,y,i-1,j);
        cout<<"delete: "<<x[i-1]<<endl;
    }
}


int main(void){
    freopen("editDistance.txt", "r", stdin);
    string x, y;
    cin>>x;
    cin>>y;

    int m = x.length();
    int n = y.length();

    int distance = editDistance(x,y,m,n);
    cout<<"Edit distance is: "<<distance<<endl;
    cout<<"Changes are: "<<endl;
    printSequence(x,y,m,n);

    return 0;
}