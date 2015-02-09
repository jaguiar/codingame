# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

declare -A horsesMap

read N
horsesArray=( $(
    for (( i=0; i<N; i++ )); do
        read Pi
        if [ "${horsesMap[$Pi]+a}" ]; then
            #already exists
            echo "0"
            return 1
        fi
        horsesMap[$Pi]="1"
        echo "$Pi"
    done | sort -n) )

minDist=999999999999999
last=-999999999999999
for i in "${horsesArray[@]}"; do
    dist=$(($i-$last))
    if [ "$dist" -lt "$minDist" ]; then
        minDist=$dist
    fi
    last=$i
done
# Write an action using echo
# To debug: echo "Debug messages..." >&2
#echo "line=${arrLine[@]} and $N" >&2
echo "$minDist"
