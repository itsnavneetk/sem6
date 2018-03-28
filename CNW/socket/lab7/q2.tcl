set ns [new Simulator]

set f [open out.tr w]
$ns trace-all $f
set nf [open out.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n2 $n1 1Mb 10ms RED
$ns duplex-link $n2 $n3 1Mb 10ms RED
$ns duplex-link $n0 $n3 1Mb 10ms RED
$ns duplex-link $n0 $n1 1Mb 10ms RED

$ns duplex-link-op $n2 $n1 orient up
$ns duplex-link-op $n2 $n3 orient left
$ns duplex-link-op $n0 $n3 orient down
$ns duplex-link-op $n0 $n1 orient right


set tcp [new Agent/TCP]
set tcp1 [new Agent/TCP]
set tcpsink [new Agent/TCPSink]
set tcpsink1 [new Agent/TCPSink]
$ns attach-agent $n0 $tcp
$ns attach-agent $n2 $tcp1
$ns attach-agent $n1 $tcpsink
$ns attach-agent $n3 $tcpsink1

$ns color 1 Red
$ns color 2 Blue
$tcp1 set fid_ 2
$tcp set fid_ 1

$ns connect $tcp1 $tcpsink1
$ns connect $tcp $tcpsink


set ftp [new Application/FTP]
$ftp attach-agent $tcp
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

proc finish {} {
global ns f nf
$ns flush-trace
close $f
close $nf
exec nam out.nam &
exit
}
$ns at 0.5 " $ftp start "
$ns at 0.5 " $ftp1 start "
$ns at 4.5 " $ftp stop "
$ns at 4.5 " $ftp1 stop "
$ns at 5.0 " finish "

$ns rtmodel-at 2 down $n1
$ns rtmodel-at 3 up $n1

$ns run
