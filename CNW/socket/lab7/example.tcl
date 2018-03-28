set ns [new Simulator]

set f [open out.tr w]
$ns trace-all $f
set nf [open out.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms RED


set tcp [new Agent/TCP]
set tcpsink [new Agent/TCPSink]
$ns attach-agent $n0 $tcp
$ns attach-agent $n1 $tcpsink

$ns color 1 Red
$tcp set fid_ 1

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
$ns at 0.5 " $ftp start "
$ns at 4.5 " $ftp stop "
$ns at 5.0 " finish "
$ns run
