/*
    111105 - Cutting Sticks
    -----------------------

    You have to cut a wood stick into several pieces. The most affordable company, Analog Cutting Machinery (ACM),
    charges money according to the length of the stick being cut. Their cutting saw allows them to make only one cut at a time.

    It is easy to see that different cutting orders can lead to different prices. For example,
    consider a stick of length 10 m that has to be cut at 2, 4, and 7 m from one end. There are several choices.
    One can cut first at 2, then at 4, then at 7. This leads to a price of 10 + 8 + 6 = 24 because the first stick was of 10 m,
    the resulting stick of 8 m, and the last one of 6 m. Another choice could cut at 4, then at 2, then at 7.
    This would lead to a price of 10 + 4 + 6 = 20, which is better for us.

    Your boss demands that you write a program to find the minimum possible cutting cost for any given stick.

    Input

    The input will consist of several input cases. The first line of each test case will contain a positive number l
    that represents the length of the stick to be cut. You can assume l < 1,000. The next line will contain the
    number n (n < 50) of cuts to be made. The next line consists of n positive numbers ci ( 0 < ci < l) representing
    the places where the cuts must be made, given in strictly increasing order.

    An input case with l = 0 represents the end of input.

    Output

    Print the cost of the minimum cost solution to cut each stick in the format shown below.
*/

#include <stdio.h>

#define MAX_DIMENSION       50

int FillMatrix(int *cuts, int nCuts)
{
    int memo[MAX_DIMENSION][MAX_DIMENSION];
    int tmpFast1, tmpFast2;
    int minSum;
    int offByV;
    int j, i;

    memo[0][1] = cuts[1];
    for(i = 1; i < nCuts - 1; ++i)
        memo[i][i+1] = cuts[i+1] - cuts[i-1];

    for(j = 2; j < nCuts; ++j)
        for(i = j; i < nCuts; ++i)
        {
            tmpFast1 = i-j;
            memo[tmpFast1][i] = cuts[i];
            if(tmpFast1-1 >= 0)
                memo[tmpFast1][i] -= cuts[tmpFast1-1];
            minSum = memo[tmpFast1+1][i];
            if(memo[tmpFast1][i-1] < minSum)
                minSum = memo[tmpFast1][i-1];
            for(offByV = tmpFast1+2; offByV < i; ++offByV)
            {
                tmpFast2 = memo[offByV][i];
                tmpFast2 += memo[tmpFast1][offByV - 1];
                if(tmpFast2 < minSum)
                    minSum = tmpFast2;
            }
            memo[tmpFast1][i] += minSum;
        }

    return memo[0][nCuts-1];
}

int main()
{
    int offsetsCuttings[MAX_DIMENSION];
    int length;
    int nCuttings;
    int j;

block:
    scanf("%d", &length);
    if(!length)
        return 0;
    scanf("%d", &nCuttings);

    j = 0;
    while(j < nCuttings)
        scanf("%d", &offsetsCuttings[j++]);

    offsetsCuttings[nCuttings++] = length;
    printf("The minimum cutting is %d.\n", FillMatrix(offsetsCuttings, nCuttings));
    goto block;
}