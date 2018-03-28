set ns [new Simulator]

set f [open out.tr w]
$ns trace-all $f
set nf [open out.nam w]
$ns namtrace-all $nf

set n0 [$ns node] 
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n0 $n2 2Mb 10ms RED
$ns duplex-link $n1 $n2 2Mb 10ms RED
$ns duplex-link $n2 $n3 1.7Mb 20ms RED

$ns duplex-link-op $n1 $n2 orient right
$ns duplex-link-op $n2 $n3 orient right

set tcp [new Agent/TCP]
set tcpsink [new Agent/TCPSink]
$ns attach-agent $n0 $tcp
$ns attach-agent $n3 $tcpsink
$ns connect $tcp $tcpsink

set ftp [new Application/FTP]
$ftp attach-agent $tcp


set udp [new Agent/UDP]
set null [new Agent/Null]
$ns attach-agent $n1 $udp
$ns attach-agent $n3 $null
$ns connect $udp $null

set cbr [new Application/Traffic/CBR] 
$cbr set packetSize_ 500 
$cbr set interval_ 0.005 
$cbr attach-agent $udp


proc finish {} {
global ns f nf 
$ns flush-trace 
close $f
close $nf
exit
}
$ns at 0.5 " $ftp start "
$ns at 4.5 " $ftp stop "
$ns at 0.5 " $cbr start "
$ns at 4.5 " $cbr stop "
$ns at 5.0 " finish "
$ns run
