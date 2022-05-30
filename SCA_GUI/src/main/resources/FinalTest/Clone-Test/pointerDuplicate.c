void sort ( int *b, int m ) {
    int i, j, t;

    if ( m < 2 ) return;

    for ( i=0 ; i < m-1; i++ ) {
        for ( j=i+1 ; j < m ; j++ ) {
            if ( b[i] > b[j] ) {
                t = b[i];
                b[i] = b[j];
                b[j] = t;
            }
        }
    }
}