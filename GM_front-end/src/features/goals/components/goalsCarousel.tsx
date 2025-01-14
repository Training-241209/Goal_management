import { Card, CardContent } from "@/components/ui/card"
import {
    Carousel,
    CarouselContent,
    CarouselItem,
    CarouselNext,
    CarouselPrevious,
} from "@/components/ui/carousel"
import { useGoals } from "../hooks/use-goals"
import { useEffect, useState } from "react";
import { cn } from "@/lib/utils";

interface GoalsCarouselProps{
    onSelect: (SelectedId: number) => void;
}
export function GoalsCarousel({onSelect}:GoalsCarouselProps) {

    const { data, isLoading } = useGoals();
    const [selectedId, setSelectedId]= useState(0);

    useEffect(()=>{
        onSelect(selectedId);
    },[selectedId])

    if (isLoading) return <p>Loading...</p>;

    return (
        <Carousel
            opts={{
                align: "start",
            }}
            orientation="vertical"
            className="w-full max-w-xs"
        >
            <CarouselContent className="-mt-1 h-[500px]">
                {data?.map(goal => (
                    <CarouselItem key={goal.id} className="pt-1 md:basis-1/5">

                        <Card className={cn('p-0 m-0',selectedId==goal.id? 'border-purple-500 border-4' :'border-none')}onClick={() => setSelectedId(goal.id)}>
                            <CardContent className="flex items-center justify-center p-6  " >
                                <span className="text-xl font-semibold text-center focus:text-purple-500">{goal.objective}</span>
                            </CardContent>
                        </Card>

                    </CarouselItem>
                ))}
            </CarouselContent>
            <CarouselPrevious />
            <CarouselNext />
        </Carousel>
    )
}
