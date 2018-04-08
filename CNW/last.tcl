set ns [new Simulator]

set f [open out.tr w]
$ns trace-all $f
set nf [open out.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms RED
$ns duplex-link $n1 $n2 1Mb 10ms RED 

set tcp [new Agent/TCP]
set tcpsink [new Agent/TCPSink]
$ns attach-agent $n0 $tcp 
$ns attach-agent $n2 $tcpsink

$ns connect $tcp $tcpsink

set ftp [new Application/FTP]
$ftp attach-agent $tcp

proc finish {} {
global ns f nf
$ns flush-trace
close $f
close $nf
exit
}

$n0 color red

$ns color 1 blue
$tcp set fid_ 1

$n0 shape box
$n1 shape hexagon

$ns at 1.0 "$n0 label \"SOURCE\""

$ns at 0.5 "$ftp start"
$ns at 5.5 "$ftp stop"
$ns at 6.0 "finish"
$ns rtmodel-at 1.0 down $n2
$ns run
